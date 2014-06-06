(function() {
    var defaultPage = sellInNamespace('sellIn.pages.default');

    angular.module('sellIn.pages.default', ['ngRoute', 'sellIn.resources.role'])
        .controller('defaultPageController', defaultPage.DefaultPageController)
        .constant('dealerUrl', '/dealers/:dealerId')
        .constant('dsmUrl', '/dsm/:id')
        .constant('dealerRoleId', 'DLR')
        .constant('dsmRoleId', 'DSM');
})();