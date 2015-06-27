var Dispatcher = require('../dispatcher/Dispatcher');

module.exports = {
    broadcast: function (message) {
        Dispatcher.dispatch({
            actionType: 'action.broadcast',
            message: message
        });
    },
    sendToUser: function (message) {
        Dispatcher.dispatch({
            actionType: 'action.sendtouser',
            message: message
        });
    }
};