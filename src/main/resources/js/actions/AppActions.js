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
    messageBroadcast: function (message) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.MESSAGE_BROADCAST,
            message: message
        });
    },
    messageToUser: function (message, user) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.MESSAGE_TO_USER,
            user: user,
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
    userSelect: function (username) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.USER_SELECT,
            username: username
        });
    },
    usersReceive: function (users) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.USERS_RECEIVE,
            users: users
        });
    },
    chatInputUpdate: function (input) {
        Dispatcher.dispatch({
            actionType: AppConstants.actions.CHAT_INPUT_UPDATE,
            input: input
        });
    }
};