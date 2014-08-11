(function () {
    var appRole = sellInNamespace('sellIn.resources.role');

    angular.module('sellIn.resources.role', ['ngResource'])
        .service('appRoleResource', appRole.AppRoleResource)
        .constant('roleUrl', '/dss-psi/webapi/roles/');

})();