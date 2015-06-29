var AppActions = require('../actions/AppActions');

function ajax(method, path, callback) {
    var r = new XMLHttpRequest();
    r.open(method, path);
    r.onreadystatechange = function () {
        if (r.readyState != 4 || r.status != 200) return;
        callback(r.responseText);
    };
    r.send();
}

module.exports = {
    getMessages: function () {
        ajax('GET', '/messages', function (responseText) {
            AppActions.messagesReceive(JSON.parse(responseText));
        });
    },
    getUsers: function () {
        ajax('GET', '/users', function (responseText) {
            AppActions.usersReceive(JSON.parse(responseText));
        });
    },
    getPrincipal: function () {
        ajax('GET', '/login/principal', function (responseText) {
            AppActions.principalReceive(JSON.parse(responseText).principal);
        });
    },
    getChannels: function () {
        ajax('GET', '/channels', function (responseText) {
            AppActions.channelsReceive(JSON.parse(responseText));
        });
    }
};