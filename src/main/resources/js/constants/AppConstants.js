var KeyMirror = require('keymirror');

module.exports = {
    actions: KeyMirror({
        WEBSOCKET_CONNECTED: null,
        WEBSOCKET_DISCONNECTED: null,
        MESSAGE_RECEIVE: null,
        MESSAGE_BROADCAST: null,
        MESSAGE_TO_USER: null,
        MESSAGES_RECEIVE: null,
        USER_CONNECT: null,
        USER_DISCONNECT: null,
        CHANNEL_SELECT: null,
        USERS_RECEIVE: null,
        PRINCIPAL_RECEIVE: null,
        CHAT_INPUT_UPDATE: null
    })
};