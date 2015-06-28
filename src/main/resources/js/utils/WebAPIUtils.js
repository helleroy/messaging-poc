var AppActions = require('../actions/AppActions');

module.exports = {
    getMessages: function () {
        var r = new XMLHttpRequest();
        r.open("GET", "/messages");
        r.onreadystatechange = function () {
            if (r.readyState != 4 || r.status != 200) return;
            AppActions.messagesReceive(JSON.parse(r.responseText));
        };
        r.send();
    },
    getUsers: function () {
        var r = new XMLHttpRequest();
        r.open("GET", "/users");
        r.onreadystatechange = function () {
            if (r.readyState != 4 || r.status != 200) return;
            AppActions.usersReceive(JSON.parse(r.responseText));
        };
        r.send();
    }
};