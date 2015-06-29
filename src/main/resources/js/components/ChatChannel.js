var React = require('react');

var AppStore = require('../stores/AppStore');
var AppActions = require('../actions/AppActions');

module.exports = React.createClass({
    handleChatInput: function (event) {
        AppActions.chatInputUpdate(event.target.value);
    },
    send: function (event) {
        event.stopPropagation();
        event.preventDefault();
        if (this.props.channel.isPersonal) {
            AppActions.messageToUser({message: this.props.input}, this.props.channel.name);
        } else {
            AppActions.messageBroadcast({message: this.props.input});
        }
        AppActions.chatInputUpdate('');
    },
    render: function () {
        return <div className="chat-channel">
            <div className="grid module chat-messages">
                <p className="chat-channel-header">
                    {(this.props.channel.isPersonal ? '@' : '#') + this.props.channel.name}
                </p>
                <ul>
                    {this.props.messages
                        .filter(function (message) {
                            if (this.props.channel.isPersonal) {
                                return this.props.channel.name === message.receiver ||
                                    (this.props.channel.name === message.sender && this.props.principal.username === message.receiver)
                            } else {
                                return message.receiver === null;
                            }
                        }.bind(this))
                        .map(function (message, index) {
                            return <li key={index}>{message.sender + ': ' + message.message}</li>
                        })}
                </ul>
            </div>
            <div className="chat-form-wrapper">
                <form className="grid col-4-5 module chat-form" onSubmit={this.send}>
                    <input type="text" className="textfield textfield-message" value={this.props.input}
                           onChange={this.handleChatInput}/>
                    <input type="submit" className="button button-send" value="Send"/>
                </form>
            </div>
        </div>;
    }
});
