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
    channel: {name: '', isPersonal: false}
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

Dispatcher.register(function (action) {
    switch (action.actionType) {
        case AppConstants.actions.MESSAGE_RECEIVE:
            state.messages.push(action.message);
            AppStore.emitChange();
            break;
        case AppConstants.actions.MESSAGES_RECEIVE:
            state.messages = action.messages;
            AppStore.emitChange();
            break;
        case AppConstants.actions.USER_CONNECT:
            state.users[action.user.name] = action.user;
            AppStore.emitChange();
            break;
        case AppConstants.actions.USER_DISCONNECT:
            delete state.users[action.user.name];
            AppStore.emitChange();
            break;
        case AppConstants.actions.USERS_RECEIVE:
            state.users = action.users;
            AppStore.emitChange();
            break;
        case AppConstants.actions.WEBSOCKET_CONNECTED:
            state.connected = true;
            AppStore.emitChange();
            break;
        case AppConstants.actions.WEBSOCKET_DISCONNECTED:
            state.connected = false;
            AppStore.emitChange();
            break;
        case AppConstants.actions.CHANNEL_SELECT:
            state.channel = action.channel;
            AppStore.emitChange();
            break;
        case AppConstants.actions.PRINCIPAL_RECEIVE:
            state.principal = action.principal;
            AppStore.emitChange();
            break;
        case AppConstants.actions.CHANNELS_RECEIVE:
            state.channels = action.channels;
            if (state.channel.name === '') {
                state.channel = action.channels[0];
            }
            AppStore.emitChange();
            break;
        case AppConstants.actions.CHAT_INPUT_UPDATE:
            state.input = action.input;
            AppStore.emitChange();
            break;
    }
});

module.exports = AppStore;