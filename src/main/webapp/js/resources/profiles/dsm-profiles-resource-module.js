(function () {
    var dsmProfiles = sellInNamespace('sellIn.resources.dsmProfiles');

    angular.module('sellIn.resources.dsmProfiles', ['ngResource'])
        .service('dsmProfilesResource', dsmProfiles.DsmProfilesResource)
        .constant('dsmCurrentProfilesUrl', '/dss-psi/webapi/dsm/profiles/:type/current')
        .constant('dsmHistoryProfilesUrl', '/dss-psi/webapi/dsm/profiles/:type/history');
})();