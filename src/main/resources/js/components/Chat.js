var React = require('react');

var AppStore = require('../stores/AppStore');
var AppActions = require('../actions/AppActions');

var ChatChannel = require('./ChatChannel');

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
    updateChannel: function (channel) {
        return function () {
            AppActions.channelSelect(channel);
        };
    },
    render: function () {
        return <div className="chat">
            <section className="grid main">
                <div className="col-1-5 sidebar chat-users">
                    <h2>Channels</h2>
                    <ul>
                        {this.state.channels.map(function (channel) {
                            return <li key={channel.name}
                                       className="sidebar-list-element"
                                       onClick={this.updateChannel({name: channel.name, isPersonal: channel.isPersonal})}>
                                {channel.name}
                            </li>
                        }.bind(this))}
                    </ul>
                    <h2>Users</h2>
                    <ul>
                        {Object.keys(this.state.users).map(function (user) {
                            return <li key={user}
                                       className="sidebar-list-element"
                                       onClick={this.updateChannel({name : this.state.users[user].name, isPersonal: true})}>
                                {this.state.users[user].name}
                            </li>
                        }.bind(this))}
                    </ul>
                </div>

                <div className="col-4-5 chat-main">
                    <ChatChannel {...this.state}/>
                </div>
            </section>
        </div>;
    },
    _onChange: function () {
        this.setState(AppStore.getState());
    }
});
