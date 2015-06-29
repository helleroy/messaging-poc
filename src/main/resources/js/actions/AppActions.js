var Dispatcher = require('../dispatcher/Dispatcher');
var AppConstants = require('../constants/AppConstants');

module.exports = {
    websocketConnected: function () {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.WEBSOCKET_CONNECTED
        });
    },
    websocketDisconnected: function () {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.WEBSOCKET_DISCONNECTED
        });
    },
    messageSend: function (message) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.MESSAGE_SEND,
            message: message
        });
    },
    messageReceive: function (message) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.MESSAGE_RECEIVE,
            message: message
        });
    },
    messagesReceive: function (messages) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.MESSAGES_RECEIVE,
            messages: messages
        });
    },
    userConnectReceive: function (user) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.USER_CONNECT,
            user: user
        });
    },
    userDisconnectReceive: function (user) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.USER_DISCONNECT,
            user: user
        });
    },
    channelSelect: function (channel) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.CHANNEL_SELECT,
            channel: channel
        });
    },
    usersReceive: function (users) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.USERS_RECEIVE,
            users: users
        });
    },
    principalReceive: function (principal) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.PRINCIPAL_RECEIVE,
            principal: principal
        });
    },
    channelsReceive: function (channels) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.CHANNELS_RECEIVE,
            channels: channels
        })
    },
    chatInputUpdate: function (input) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.CHAT_INPUT_UPDATE,
            input: input
        });
    }
};