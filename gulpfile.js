var source = require('vinyl-source-stream');
var gulp = require('gulp');
var gutil = require('gulp-util');
var less = require('gulp-less');
var browserify = require('browserify');
var reactify = require('reactify');
var plumber = require('gulp-plumber');
var notify = require("gulp-notify");
var uglifycss = require('gulp-uglifycss');
var uglify = require('gulp-uglify');
var streamify = require('gulp-streamify');

var jsSrcDir = './src/main/resources/js/';
var jsBuildDir = './src/main/resources/static/js/';
var lessSrcDir = './src/main/resources/less/';
var cssBuildDir = './src/main/resources/static/css/';

function createBrowserify() {
    return browserify({
        entries: [jsSrcDir + 'app.js'],
        transform: [reactify],
        debug: false
    });
}

gulp.task('script-build', function () {
    gutil.log('Compiling scripts...');
    var start = new Date();
    createBrowserify().bundle()
        .on('error', notify.onError({message: '<%= error.message %>'}))
        .pipe(source('app.js'))
        .pipe(streamify(uglify()))
        .pipe(gulp.dest(jsBuildDir))
        .pipe(notify('Build completed in ' + (new Date() - start) + 'ms.'));
});

gulp.task('style', function () {
    gutil.log('Compiling LESS...');
    gulp.src(lessSrcDir + 'main.less')
        .pipe(plumber())
        .pipe(less())
        .pipe(uglifycss())
        .pipe(gulp.dest(cssBuildDir))
});

gulp.task('script-dev', function () {
    gutil.log('Compiling scripts...');
    var start = new Date();
    createBrowserify().bundle()
        .on('error', notify.onError({message: '<%= error.message %>'}))
        .pipe(source('app.js'))
        .pipe(gulp.dest(jsBuildDir))
        .pipe(notify('Build completed in ' + (new Date() - start) + 'ms.'));
});

gulp.task('watch', ['script-dev', 'style'], function () {
    gutil.log('Watching for changes...');
    gulp.watch(jsSrcDir + '**/*.js', ['script-dev']);
    gulp.watch(lessSrcDir + '**/*.less', ['style']);
});

gulp.task('build', ['script-build', 'style']);

gulp.task('default', ['watch']);
