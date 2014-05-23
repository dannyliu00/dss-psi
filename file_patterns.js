
var FilePatterns = {
	gruntFiles: ['Gruntfile.js', 'file_patterns.js'],
	templateHtml: ['src/main/webapp/js/**/*.html'],
	nonTemplateHtml: ['src/main/webapp/index.html'],

	thirdPartyAssets: [
		'src/main/webapp/bower_components/bootstrap/dist/css/bootstrap.css',
		'src/main/webapp/bower_components/normalize-css/normalize.css',
		'src/main/webapp/bower_components/jquery-ui/themes/base/minified/jquery-ui.min.css'
	],

	thirdPartyJavascript: [
		'src/main/webapp/bower_components/jquery/dist/jquery.js',
		'src/main/webapp/bower_components/jquery-ui/ui/jquery-ui.js',
		'src/main/webapp/bower_components/angular/angular.js',
		'src/main/webapp/bower_components/angular-route/angular-route.js',
		'src/main/webapp/bower_components/angular-resource/angular-resource.js',
		'src/main/webapp/bower_components/bootstrap/dist/js/bootstrap.js',
		'src/main/webapp/bower_components/angular-sanitize/angular-sanitize.min.js'
	],

	thirdPartyJavascriptForSpecs: [
		'src/main/webapp/bower_components/angular-mocks/angular-mocks.js'
	],

	source: [
		// namespace needs to be initialized before other files are read
		'src/main/webapp/js/utilities/namespace/*.js',
		'src/main/webapp/js/initialize_namespace.js',

		// all javascripts
		'src/main/webapp/js/**/*.js',

		// reorder modules toward the bottom
		'!src/main/webapp/js/**/*-module.js',
		'src/main/webapp/js/**/*-module.js',

		// reorder app.js to be last
		'!src/main/webapp/js/app.js',
		'src/main/webapp/js/app.js'
	],

	specs: [
		'src/test/js/**/*_spec.js'
	],

	generatedSource: [
	],

	manifestCache: [
	],

	manifest: [
	]
};

var removeAppFromPathBase = function(path) {
	return path.replace(/^app\//, '');
};

var FilePatternsInterface = {
	watch: FilePatterns.thirdPartyJavascript.concat(FilePatterns.gruntFiles, FilePatterns.source, FilePatterns.templateHtml, FilePatterns.nonTemplateHtml, FilePatterns.specs),
	javascript: {
		jshint: FilePatterns.source.concat(FilePatterns.gruntFiles, FilePatterns.specs),
		specs: FilePatterns.thirdPartyJavascript.concat(FilePatterns.thirdPartyJavascriptForSpecs, FilePatterns.source, FilePatterns.specs),
		concat: FilePatterns.source.concat(FilePatterns.generatedSource)
	},
	manifest: FilePatterns.manifest.concat(FilePatterns.thirdPartyAssets.map(removeAppFromPathBase), FilePatterns.thirdPartyJavascript.map(removeAppFromPathBase)),
	manifestCache: FilePatterns.manifestCache
};

module.exports.FilePatterns = FilePatternsInterface;