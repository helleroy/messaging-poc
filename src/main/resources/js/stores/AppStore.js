var EventEmitter = require('events').EventEmitter;
var _ = require('lodash');

var Dispatcher = require('../dispatcher/Dispatcher');
var AppConstants = require('../constants/AppConstants');

var CHANGE_EVENT = 'changed';

var state = {
    messages: [],
    users: [],
    channels: [],
    connected: false,
    principal: {},
    input: '',
    channel: {name: '', personal: false}
};

var AppStore = _.assign({}, EventEmitter.prototype, {
    getState: function () {
        return state;
    },
    emitChange: function () {
        this.emit(CHANGE_EVENT);
    },
    addChangeListener: function (callback) {
        this.on(CHANGE_EVENT, callback);
    },
    removeChangeListener: function (callback) {
        this.removeListener(CHANGE_EVENT, callback);
    }
});

function markChannelNewMessage(channel, mark) {
    return state.channel.name === channel.name ?
        state.channels :
        state.channels.map(function (c) {
            if (c.name === channel.name) {
                return _.assign({}, c, {newMessage: mark});
            }
            return c;
        });
}

function markUserNewMessage(username, mark) {
    if (state.channel.name !== username && username in state.users) {
        state.users[username] = _.assign({}, state.users[username], {newMessage: mark});
    }
    return state.users;
}

Dispatcher.register(function (action) {
    switch (action.actionType) {
        case AppConstants.actions.MESSAGE_RECEIVE:
            if (action.message.channel.personal) {
                state.users = markUserNewMessage(action.message.sender, true);
            } else {
                state.channels = markChannelNewMessage(action.message.channel, true);
            }
            state.messages.push(action.message);
            break;
        case AppConstants.actions.MESSAGES_RECEIVE:
            state.messages = action.messages;
            break;
        case AppConstants.actions.USER_CONNECT:
            state.users[action.user.name] = action.user;
            break;
        case AppConstants.actions.USER_DISCONNECT:
            delete state.users[action.user.name];
            break;
        case AppConstants.actions.USERS_RECEIVE:
            delete action.users[state.principal.username];
            state.users = action.users;
            break;
        case AppConstants.actions.WEBSOCKET_CONNECTED:
            state.connected = true;
            break;
        case AppConstants.actions.WEBSOCKET_DISCONNECTED:
            state.connected = false;
            break;
        case AppConstants.actions.CHANNEL_SELECT:
            if (action.channel.personal) {
                state.users = markUserNewMessage(action.channel.name, false);
            } else {
                state.channels = markChannelNewMessage(action.channel, false);
            }
            state.channel = action.channel;
            break;
        case AppConstants.actions.PRINCIPAL_RECEIVE:
            state.principal = action.principal;
            delete state.users[action.principal.username];
            break;
        case AppConstants.actions.CHANNELS_RECEIVE:
            state.channels = action.channels;
            if (state.channel.name === '') {
                state.channel = action.channels[0];
            }
            break;
        case AppConstants.actions.CHAT_INPUT_UPDATE:
            state.input = action.input;
            break;
        default:
            return;
    }

    AppStore.emitChange();

});

module.exports = AppStore;