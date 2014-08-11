/**
 * Created by bericks on 5/13/2014.
 */
(function () {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    function DealerProfileSummaryCtrl($scope, $routeParams, $location, dealerProfilesResource, profilePageUrl, dealerSummaryPageUrl, lastTab, blockUI) {

        this.location = $location;

        var loadProfiles = function (status) {
            // Block the user interface
            blockUI.start();

            lastTab.changeProfilesTab(status);

            var rVal = new Date().getTime();

            var profile = {r: rVal};

            if (status === 'current') {
                dealerProfilesResource.queryCurrent(profile).then(function (profiles) {
                    $scope.profiles = profiles;
                });
            } else {
                dealerProfilesResource.queryHistory(profile).then(function (profiles) {
                    $scope.profiles = profiles;
                });
            }
            // Unblock the user interface
            blockUI.stop();
        };

        loadProfiles($routeParams.status);

        $scope.navigateToProfile = function (profileId, type) {
            lastTab.changeProductTab('');
            var finalUrl = profilePageUrl.replace(':profileId', profileId)
                .replace(':type', type);
            $location.path(finalUrl);
        };

        $scope.$on('tabClick', function (event, data) {
            lastTab.changeProfilesTab(data);
            var type = $routeParams.type;
            var finalUrl = dealerSummaryPageUrl
                .replace(':type', type)
                .replace(':status', data);
            $location.path(finalUrl);
        });

    }

    dealerProfileSummary.DealerProfileSummaryCtrl = DealerProfileSummaryCtrl;
})();
