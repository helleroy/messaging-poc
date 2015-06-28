var React = require('react');

var Chat = require('./components/Chat');

var AppActions = require('./actions/AppActions');
var WebSocketAPIUtils = require('./utils/WebSocketAPIUtils');
var WebAPIUtils = require('./utils/WebAPIUtils');

React.render(<Chat/>, document.getElementById('app'), function () {
    WebSocketAPIUtils.connect(function () {
        WebAPIUtils.getMessages();
        WebAPIUtils.getUsers();
        AppActions.websocketConnected();
    });
});
