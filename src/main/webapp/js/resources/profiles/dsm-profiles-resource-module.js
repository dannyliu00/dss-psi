(function() {
    var dsmProfiles = sellInNamespace('sellIn.resources.dsmProfiles');

    angular.module('sellIn.resources.dsmProfiles', ['ngResource'])
        .service('dsmProfilesResource', dsmProfiles.DsmProfilesResource)
        .constant('dsmProfilesUrl', '/dss-psi/webapi/dsm/profiles/:dsmId');
})();