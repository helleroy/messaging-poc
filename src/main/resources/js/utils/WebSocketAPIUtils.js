var SockJS = require('sockjs-client');
var Stomp = require('stompjs');

var Dispatcher = require('../dispatcher/Dispatcher');
var AppActions = require('../actions/AppActions');
var AppConstants = require('../constants/AppConstants');

var stompClient;

var connect = function (callback) {
    stompClient = Stomp.over(new SockJS('/message'));
    stompClient.connect({}, function () {
        stompClient.subscribe('/chat/messages', function (message) {
            AppActions.messageReceive(JSON.parse(message.body));
        });
        stompClient.subscribe('/user/chat/messages', function (message) {
            AppActions.messageReceive(JSON.parse(message.body));
        });
        stompClient.subscribe('/chat/users', function (message) {
            var connectionMessage = JSON.parse(message.body);
            if (connectionMessage.connected) {
                AppActions.userConnectReceive(connectionMessage.user);
            } else {
                AppActions.userDisconnectReceive(connectionMessage.user);
            }
        });

        return typeof callback === "function" ? callback() : void 0;
    });
};

var disconnect = function (callback) {
    stompClient.disconnect(function () {
        return typeof callback === "function" ? callback() : void 0;
    });
};

Dispatcher.register(function (action) {
    switch (action.actionType) {
        case AppConstants.actions.MESSAGE_BROADCAST:
            stompClient.send('/message', {}, JSON.stringify(action.message));
            break;
        case AppConstants.actions.MESSAGE_TO_USER:
            stompClient.send('/user/' + action.user + '/message', {}, JSON.stringify(action.message));
            break;
    }
});

module.exports = {
    connect: connect,
    disconnect: disconnect
};
