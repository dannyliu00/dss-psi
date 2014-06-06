(function() {
    var defaultPage = sellInNamespace('sellIn.pages.default');

    function DefaultPageController($scope, $location, appRoleResource, dealerUrl, dsmUrl, dsmRoleId) {

        appRoleResource.get().then(function(role) {
            $scope.role = role;
        }).then(function() {
            switch ($scope.role.role) {
                case dsmRoleId:
                    var finalUrl = dsmUrl.replace(':id', $scope.role.id);
                    $location.path(finalUrl);
                    break;
                default:
                    var finalUrl = dealerUrl.replace(':dealerId', $scope.role.dealerId);
                    $location.path(finalUrl);
                    break;
            }
        });
    }

    defaultPage.DefaultPageController = DefaultPageController;
})();