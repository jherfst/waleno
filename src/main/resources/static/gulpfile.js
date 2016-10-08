var gulp = require('gulp');

var uglify = require('gulp-uglify');
var concat = require('gulp-concat');
var cssMin = require('gulp-css');
var minifyCss = require('gulp-minify-css');
var ngAnnotate = require('gulp-ng-annotate');

gulp.task('compress-styles', function() {
	return gulp.src([
	                 './app/common/css/bootstrap.css',
	                 './app/common/css/bootstrap.wizard.css',
	                 './app/common/css/font-awesome.css',
	                 '../../app/common/css/bootstrap.wizard.css',
	                 './bower_components/jquery-ui/themes/flick/jquery-ui.min.css',
	                 './bower_components/toaster/toaster.min.css',
	                 './bower_components/formly/angular-wizard.css',
	                 './bower_components/formly/select.css',
	                 '../bower_components/formly/select.css',
	                 './bower_components/formly/nya-bs-select.min.css',
	                 './bower_components/angular-data-grid/ui.grid.min.css',
	                 './bower_components/ng-tags-input/ng-tags-input.min.css',
	                 './bower_components/ng-tags-input/ng-tags-input.bootstrap.min.css',
	                 './bower_components/angular-block-ui/dist/angular-block-ui.min.css',
	                 './bower_components/angular-busy/dist/angular-busy.min.css',
	                 './app/common/css/styles.css'
	         ])
	        .pipe(concat('styles.min.css'))
	  		.pipe(gulp.dest('./app/dist/'));
});



gulp.task('compress-app', function() {
	return gulp.src([
	                 './app/*.js',
	                 './app/account/**/**/*.js',
	                 './app/home/**/*.js',
	                 './app/navigation/**/*.js',
	                 './app/progress/**/*.js',
	                 './app/requests/**/*.js',
	                 './app/success/**/*.js',
	                 './app/common/services/*.js',
	                 './app/common/filters/*.js',
	                 './app/common/directives/common.directives.js',
	         ])
	        .pipe(concat('app.min.js'))
	        .pipe(ngAnnotate())
	        .pipe(uglify())
	  		.pipe(gulp.dest('./app/dist/'));
});



gulp.task('watch',function() {
	gulp.watch('./app/*.js', ['compress-app']);
	gulp.watch('./app/account/**/**/*.js', ['compress-app']);
	gulp.watch('./app/home/**/*.js', ['compress-app']);
	gulp.watch('./app/navigation/**/*.js', ['compress-app']);
	gulp.watch('./app/progress/**/*.js', ['compress-app']);
	gulp.watch('./app/requests/**/*.js', ['compress-app']);
	gulp.watch('./app/success/**/*.js', ['compress-app']);
	gulp.watch('./app/common/services/*.js', ['compress-app']);
	gulp.watch('./app/common/filters/*.js', ['compress-app']);
	gulp.watch('./app/common/directives/*.js', ['compress-app']);
});


gulp.task('compress-thirtparty', function() {
	return gulp.src([
	                 
	                 './bower_components/angular-ui-router/release/angular-ui-router.min.js',
	                 './bower_components/angular-resource/angular-resource.min.js',
	                 './bower_components/angular-cookies/angular-cookies.js',
	                 './bower_components/ng-idle/angular-idle.min.js',
	                 './bower_components/bootstrap/dist/js/bootstrap.min.js',
	                 './bower_components/jquery-ui/ui/i18n/datepicker-en-GB.js',
	                 './bower_components/jquery-ui/ui/i18n/datepicker-nl.js',
	                 './bower_components/toaster/toaster.min.js',
	                 './bower_components/formly/api-check.js',
	                 './bower_components/formly/lodash.js',
	                 './bower_components/formly/angular-wizard.js',
	                 './bower_components/formly/formly.js',
	                 './bower_components/formly/angular-formly-templates-bootstrap.js',
	                 './bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js',
	                 './bower_components/formly/select.js',
	                 './bower_components/formly/dropzone.js',
	                 './bower_components/formly/ngMask.min.js',
	                 './bower_components/formly/angular-messages.js',
	                 './app/common/directives/formly-config.js',
	                 './bower_components/angular-sanitize/angular-sanitize.min.js',
	                 './bower_components/angular-translate/angular-translate.js',
	                 './bower_components/angular-translate/angular-translate-loader-static-files.js',
	                 './bower_components/angular-translate/angular-translate-storage-local.js',
	                 './bower_components/angular-data-grid/ui.grid.min.js',
	                 './bower_components/angular-smart-table/dist/smart-table.min.js',
	                 './bower_components/dir-pagination/dir-pagination.js',
	                 './bower_components/ng-tags-input/ng-tags-input.min.js',
	                 './bower_components/angular-money-directive/angular-money-directive.js',
	                 './bower_components/ng-file-upload/ng-file-upload-shim.min.js',
	                 './bower_components/ng-file-upload/ng-file-upload.min.js',
	                 './bower_components/moment/min/moment.min.js',
	                 './bower_components/angular-moment-module/lib/angular-moment-module.js',
	                 './bower_components/angular-busy/dist/angular-busy.min.js',
	                 './bower_components/angular-recaptcha/angular-recaptcha.min.js',
	                 './bower_components/ng-file-upload/ng-file-upload-shim.min.js',
	                 './bower_components/angular-block-ui/dist/angular-block-ui.min.js',
	                 './bower_components/jsPdf/dist/jspdf.min.js',
	                 './bower_components/angularUtils-pagination/dirPagination.js',
	                 './app/common/directives/errorSummary.js',
	                 './app/common/directives/formly-config',
	                 './app/common/directives/jqueryDatePicker.js'
	         ])
	        .pipe(concat('thirtparty.min.js'))
	  		.pipe(gulp.dest('./app/dist/'));
});

gulp.task('default', [
                      'compress-styles',
                      'compress-thirtparty',
                      'watch']);


/*gulp.task('default', ['compress-styles']);*/

