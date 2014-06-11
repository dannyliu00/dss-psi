(function() {
    var reasonCode = sellInNamespace('sellIn.resources.reasoncode');

    angular.module('sellIn.resources.reasoncode', ['ngResource'])
        .service('reasonCode', reasonCode.ReasonCodeResource)
        .constant('reasonCodeUrl', '/dss-psi/webapi/reasons/:roleId');
})();