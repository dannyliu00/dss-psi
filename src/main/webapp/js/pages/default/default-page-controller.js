(function () {
    var defaultPage = sellInNamespace('sellIn.pages.default');

    function DefaultPageController($scope,$rootScope, $location, appRoleResource,translationResource, dealerSummaryPageUrl, dsmUrl, dsmRoleId, rsmRoleId,translationPSI) {
        var finalUrl = '/';

        
        appRoleResource.get().then(function (role) {
            $scope.role = role;
        }).then(function () {

            var type = null;

            switch ($scope.role.customerClass) {
                case dsmRoleId:
                case rsmRoleId:
                    type = getProductType($scope.role);
                    finalUrl = dsmUrl
                        .replace(':type', type)
                        .replace(':status', 'current');
                    break;
                default:
                    type = getProductType($scope.role);
                    finalUrl = dealerSummaryPageUrl
                        .replace(':type', type)
                        .replace(':status', 'current');
            }
        }).then(function () {
            $location.path(finalUrl);
        });

        var getProductType = function (role) {
            if (role.sessionDetail.ATV === 'Y') {
                return '2';
            }
            if (role.sessionDetail.RGR === 'Y') {
                return '6';
	    };
            }
            if (role.sessionDetail.RZR === 'Y') {
                return 'Z';
            }
            if (role.sessionDetail.VIC === 'Y') {
                return '5';
            }
            if (role.sessionDetail.IND === 'Y') {
                return 'F';
            }
            if (role.sessionDetail.MIL === 'Y') {
                return '9';
            }
            if (role.sessionDetail.OSP === 'Y') {
                return 'D';
            }
        };
//    		  }
//    		};

    defaultPage.DefaultPageController = DefaultPageController;
})();
