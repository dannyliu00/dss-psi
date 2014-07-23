(function() {
    var rsmProfiles = sellInNamespace('sellIn.resources.rsmProfiles');

    angular.module('sellIn.resources.rsmProfiles', ['ngResource'])
        .service('rsmProfilesResource', rsmProfiles.RsmProfilesResource)
        .constant('rsmCurrentProfilesUrl', '/dss-psi/webapi/rsm/profiles/:rsmId/:type/current')
        .constant('rsmHistoryProfilesUrl', '/dss-psi/webapi/rsm/profiles/:rsmId/:type/history');
})();