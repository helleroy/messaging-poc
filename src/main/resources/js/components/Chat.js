var React = require('react');

var AppStore = require('../stores/AppStore');
var AppActions = require('../actions/AppActions');

module.exports = React.createClass({
    getInitialState: function () {
        return AppStore.getState();
    },
    componentDidMount: function () {
        AppStore.addChangeListener(this._onChange);
    },
    componentDidUnmount: function () {
        AppStore.removeChangeListener(this._onChange);
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
                        <p className="chat-channel-header">
                            {this.state.selectedUser.length !== 0 ? '@' + this.state.selectedUser : '#everyone'}
                        </p>
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
                            <input type="submit" className="button button-send" value="Send"/>
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
