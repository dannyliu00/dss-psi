(function() {
    var defaultPage = sellInNamespace('sellIn.pages.default');

    function DefaultPageController($scope, appRoleResource) {
        this.scope = $scope;

    }

    defaultPage.DefaultPageController = DefaultPageController;
})();