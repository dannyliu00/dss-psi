(function() {
    var defaultPage = sellInNamespace('sellIn.pages.default');

    function DefaultPageController($scope, $location, appRoleResource, dealerSummaryPageUrl, dsmUrl, dsmRoleId, rsmRoleId) {
        var finalUrl = '/';

        appRoleResource.get().then(function(role) {
            $scope.role = role;
        }).then(function() {
            switch ($scope.role.customerClass) {
                case dsmRoleId:
                case rsmRoleId:
                    finalUrl = dsmUrl.replace(':id', $scope.role.dealerId);
                    break;
                default:
                    finalUrl = dealerSummaryPageUrl.replace(':dealerId', $scope.role.dealerId);
            }
        }).then(function() {
            $location.path(finalUrl);
        });
    }

    defaultPage.DefaultPageController = DefaultPageController;
})();