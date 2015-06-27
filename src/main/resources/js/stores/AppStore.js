var EventEmitter = require('events').EventEmitter;
var _ = require('lodash');

var Dispatcher = require('../dispatcher/Dispatcher');

var AppStore = _.assign({}, EventEmitter.prototype, {
    getState: function () {
        return state;
    },
    emitChange: function () {
        this.emit(CHANGE_EVENT);
    },
    addChangeListener: function (callback) {
        this.on(CHANGE_EVENT, callback);
    },
    removeChangeListener: function (callback) {
        this.removeListener(CHANGE_EVENT, callback);
    }
});


Dispatcher.register(function (action) {
});

module.exports = AppStore;