(function () {
    var reasonCode = sellInNamespace('sellIn.resources.reasoncode');

    angular.module('sellIn.resources.reasoncode', ['ngResource'])
        .service('reasonCodeResource', reasonCode.ReasonCodeResource)
        .constant('reasonCodeUrl', '/dss-psi/webapi/reasons/:roleId');
})();