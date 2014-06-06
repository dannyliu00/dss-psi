(function() {
    var defaultPage = sellInNamespace('sellIn.pages.default');

    angular.module('sellIn.pages.default', ['ngRoute', 'sellIn.resources.role', 'sellIn.routing.paths'])
        .controller('defaultPageController', defaultPage.DefaultPageController)
        .constant('dsmUrl', '/dsmSummary/:id')
        .constant('dsmRoleId', 'DSM');
})();