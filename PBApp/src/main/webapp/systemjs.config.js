/**
 * System configuration for Angular 2 samples
 * Adjust as necessary for your application needs.
 */
(function(global) {
  // map tells the System loader where to look for things
  var map = {
    'app':                        'app', // 'dist',
    '@angular':                   'node_modules/@angular',
    'angular2-in-memory-web-api': 'node_modules/angular2-in-memory-web-api',
    'angular2-datatable':         'node_modules/angular2-datatable',
    'rxjs':                       'node_modules/rxjs',
    'lodash':                     'https://npmcdn.com/lodash@4.6.1/lodash.js',
    'ng2-select':                 'node_modules/ng2-select',
    'angular2-modal':             'node_modules/angular2-modal',
    'ng2-bs3-modal':              'node_modules/ng2-bs3-modal'
  };
  // packages tells the System loader how to load when no filename and/or no extension
  var packages = {
    'app':                              { main: 'boot.js',  defaultExtension: 'js' },
    'rxjs':                             { defaultExtension: 'js' },
    'angular2-in-memory-web-api':       { defaultExtension: 'js' },
    'angular2-datatable':               { defaultExtension: 'js' },
    'ng2-select':                       { defaultExtension: 'js' },
    'angular2-modal':                   { defaultExtension: 'js', main: 'index' },
    'angular2-modal/plugins/bootstrap': { defaultExtension: 'js', main: 'index' },
    'angular2-modal/plugins/vex':       { defaultExtension: 'js', main: 'index' },
    'ng2-bs3-modal':                   { defaultExtension: 'js', main: 'index' }
  };
  var ngPackageNames = [
    'common',
    'compiler',
    'core',
    'http',
    'platform-browser',
    'platform-browser-dynamic',
    'router',
    'router-deprecated',
    'upgrade',
  ];
   // Individual files (~300 requests):
  function packIndex(pkgName) {
    packages['@angular/'+pkgName] = { main: 'index.js', defaultExtension: 'js' };
  }

  // Bundled (~40 requests):
  function packUmd(pkgName) {
    packages['@angular/'+pkgName] = { main: '/bundles/' + pkgName + '.umd.js', defaultExtension: 'js' };
  }

  // Most environments should use UMD; some (Karma) need the individual index files
  var setPackageConfig = System.packageWithIndex ? packIndex : packUmd;

  // Add package entries for angular packages
  ngPackageNames.forEach(setPackageConfig);

  // No umd for router yet
  packages['@angular/router'] = { main: 'index.js', defaultExtension: 'js' };

  var config = {
    map: map,
    packages: packages
  };

  System.config(config);

})(this);