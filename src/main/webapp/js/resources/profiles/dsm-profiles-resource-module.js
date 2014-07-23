(function() {
    var dsmProfiles = sellInNamespace('sellIn.resources.dsmProfiles');

    angular.module('sellIn.resources.dsmProfiles', ['ngResource'])
        .service('dsmProfilesResource', dsmProfiles.DsmProfilesResource)
        .constant('dsmCurrentProfilesUrl', '/dss-psi/webapi/dsm/profiles/:dsmId/:type/current')
        .constant('dsmHistoryProfilesUrl', '/dss-psi/webapi/dsm/profiles/:dsmId/:type/history');
})();