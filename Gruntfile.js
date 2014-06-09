
var FilePatterns = require('./file_patterns.js').FilePatterns;

module.exports = function(grunt) {
	grunt.initConfig({

		jshint: {
			files: FilePatterns.javascript.jshint,
			options: {
				bitwise: true,      // prohibit use of bitwise operators such as ^ (XOR), | (OR) and others
				curly: false,       // requires you to always put curly braces around blocks in loops and conditionals
				eqeqeq: false,      // prohibits the use of == and != in favor of === and !==
				eqnull: true,       // suppresses warnings about == null comparisons
				forin: true,        // requires all for in loops to filter object's items
				indent: 4,          // char size of indents
				latedef: false,     // prohibits the use of a variable before it was defined
				undef: true,        // prohibits the use of explicitly undeclared variables
				strict: false,      // requires all functions to run in ECMAScript 5's strict mode
				trailing: true,     // makes it an error to leave a trailing whitespace in your code
				maxerr: 50,         // maximum amount of warnings JSHint will produce before giving up
				smarttabs: false,   // suppresses warnings about mixed tabs and spaces when the latter are used for alignment only
				globals: {
					angular: true,
					jasmine: true,
					beforeEach: true,
					afterEach: true,
					describe: true,
					inject: true,
					expect: true,
					it: true,
					sellIn: true,
					console: true,
					namespaceUtility: true,
					spyOn: true,
					createSellInNamespace: true,
					sellInNamespace: true
				}
			}
		},

		watch: {
			livereload: {
				options: {
					livereload: '<%= connect.livereload.options.livereload %>'
				},
				files: grunt.file.expand(FilePatterns.watch)
			},
			test: {
				files: grunt.file.expand(FilePatterns.watch),
				tasks: ['jshint', 'karma:unit:run']
			}
		},

		connect: {
			options: {
				port: 9000,
				host: 'localhost',
				base: 'src/main/webapp'
			},
			livereload: {
				options: {
					open: false,
					livereload: 35729
				}
			},
			headless: {
				options: {
					open: false
				}
			}
		},

		exec: {
			cucumberjs: {
				command: 'cucumber-js -f pretty <%= cucumberjs.files %>'
			}
		},

		cucumberjs: {
			files: grunt.option('files') || ''
		},

		karma: {
			options: {
				files: grunt.file.expand(FilePatterns.javascript.specs)
			},

			unit: {
                configFile: 'karma.conf.js',
                singleRun: false,
				background: true
			},

			continuous: {
                configFile: 'karma.conf.js',
				singleRun: true,
				browsers: ['PhantomJS']
			}
		},

		mochaTest: {
			endpoints: {
				src: grunt.file.expand([
					'src/test/js/endpoints/**/*.js'
				])
			}
		},

		'bower-install': {
			target: {
				src: ['src/main/webapp/index.html'],
				ignorePath: 'src/main/webapp/',
				jsPattern: '<script type="text/javascript" src="{{filePath}}"></script>',
				cssPattern: '<link href="{{filePath}}" rel="stylesheet" />'
			}
		},

		bump: {
			options: {
				commit: true,
				createTag: true,
				tagName: 'v%VERSION%',
				tagMessage: 'Version %VERSION%',
				push: false
			}
		},

		concat: {
			options: {
				seperator: ';'
			},
			dist: {
				src: FilePatterns.javascript.concat,
				dest: 'target/dss-psi/js/psi.js'
			}
		},

		processhtml: {
			build: {
				files: {
					'target/dss-psi/index.html': ['src/main/webapp/index.html']
				}
			}
		},

		manifest: {
			generate: {
				options: {
					basePath: 'build',
					cache: FilePatterns.manifestCache,
					network: ['*'],
					verbose: false,
					timestamp: true
				},
				src: FilePatterns.manifest,
				dest: 'target/dss-psi/manifest.appcache'
			}
		},

		clean: {
			build: ['build/*', 'temp/*', 'dist/*'],
			bower: ['.bower', 'app/bower_components']
		},

		html2js: {
			options: {
				base: 'src/main/webapp',
				module: 'psi-templates'
			},
			main: {
				src: ['src/main/webapp/**/*.html'],
				dest: 'temp/psi-templates.js'
			}
		},

		copy: {
			main: {
				expand: true,
				cwd: 'src/main/webapp/',
				src: ['css/**', 'img/**', 'icons/**', 'libs/**', 'bower_components/**', '*.*', '!index.html', '!manifest.appcache'],
				dest: 'build/'
			}
		}
	});

	grunt.registerTask('label', function() {
		var buildLabel = grunt.option('buildLabel');
		if (buildLabel) {
			grunt.file.write('build/buildLabel', buildLabel);
		}
	});

	grunt.registerTask('default', []);

	grunt.registerTask('cucumberjs', ['connect:headless', 'exec:cucumberjs']);
	grunt.registerTask('server', ['connect:livereload', 'watch:livereload']);
	grunt.registerTask('dev', ['karma:unit:start', 'watch:test']);
	grunt.registerTask('build', ['clean:build', 'jshint', 'karma:continuous', 'label', 'html2js', 'copy', 'manifest', 'compress']);

	grunt.loadNpmTasks('grunt-contrib-connect');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-karma');
	grunt.loadNpmTasks('grunt-bower-install');
	grunt.loadNpmTasks('grunt-contrib-jshint');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-contrib-copy');
};
