(function() {
    var defaultPage = sellInNamespace('sellIn.pages.default');

    angular.module('sellIn.pages.default', ['ngRoute', 'sellIn.resources.role', 'sellIn.routing.paths','sellIn.services.translation'])
        .controller('defaultPageController', defaultPage.DefaultPageController)
        .constant('dsmRoleId', 'SLS')
        .constant('rsmRoleId','RSM');
})();