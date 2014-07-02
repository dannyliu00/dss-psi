(function() {
    var rsmProfiles = sellInNamespace('sellIn.resources.rsmProfiles');

    angular.module('sellIn.resources.rsmProfiles', ['ngResource'])
        .service('rsmProfilesResource', rsmProfiles.RsmProfilesResource)
        .constant('rsmProfilesUrl', '/dss-psi/webapi/rsm/profiles/:rsmId/:type');
})();