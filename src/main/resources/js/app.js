var React = require('react');

var AppStore = require('./stores/AppStore');
var AppActions = require('./actions/AppActions');
var WebSocketAPIUtils = require('./utils/WebSocketAPIUtils');
var WebAPIUtils = require('./utils/WebAPIUtils');


var App = React.createClass({
    getInitialState: function () {
        return AppStore.getState();
    },
    componentDidMount: function () {
        AppStore.addChangeListener(this._onChange);
        this.connect();
    },
    componentDidUnmount: function () {
        AppStore.removeChangeListener(this._onChange);
    },
    connect: function () {
        WebSocketAPIUtils.connect(function () {
            WebAPIUtils.getMessages();
            WebAPIUtils.getUsers();
            AppActions.websocketConnected();
        });
    },
    disconnect: function () {
        WebSocketAPIUtils.disconnect(function () {
            AppActions.websocketDisconnected();
        });
    },
    handleChatInput: function (event) {
        AppActions.chatInputUpdate(event.target.value);
    },
    updateToUser: function (user) {
        var name = user.name === this.state.selectedUser ? '' : user.name;
        return function () {
            AppActions.userSelect(name);
        };
    },
    send: function (event) {
        event.stopPropagation();
        event.preventDefault();
        if (this.state.selectedUser.length === 0) {
            AppActions.messageBroadcast({message: this.state.input});
        } else {
            AppActions.messageToUser({message: this.state.input}, this.state.selectedUser);
        }
        AppActions.chatInputUpdate('');
    },
    render: function () {
        return <div className="chat">
            <section className="grid header">
                <span>Sending to: {this.state.selectedUser.length !== 0 ? '@' + this.state.selectedUser : '#everyone'}</span>
                <span>Connected: {this.state.connected ? 'Yes' : 'No'}</span>
                <input type="button"
                       className="button-small"
                       value={this.state.connected ? "Disconnect" : "Reconnect"}
                       onClick={this.state.connected ? this.disconnect : this.connect}/>
            </section>
            <section className="grid main">
                <div className="col-1-5 sidebar chat-users">
                    <ul>
                        {Object.keys(this.state.users).map(function (user) {
                            return <li key={user}
                                       className="sidebar-list-element"
                                       onClick={this.updateToUser(this.state.users[user])}>
                                {this.state.users[user].name}
                            </li>
                        }.bind(this))}
                    </ul>
                </div>
                <div className="col-4-5 chat-main">
                    <div className="grid module chat-messages">
                        <ul>
                            {this.state.messages.map(function (message, index) {
                                return <li key={index}>{message.sender + ': ' + message.message}</li>
                            })}
                        </ul>
                    </div>
                    <div className="chat-form-wrapper">
                        <form className="grid col-4-5 module chat-form" onSubmit={this.send}>
                            <input type="text" className="textfield textfield-message" value={this.state.input}
                                   onChange={this.handleChatInput}/>
                            <input type="submit" className="button-large button-send" value="Send"/>
                        </form>
                    </div>
                </div>
            </section>
        </div>;
    },
    _onChange: function () {
        this.setState(AppStore.getState());
    }
});

React.render(<App/>, document.getElementById('app'));
