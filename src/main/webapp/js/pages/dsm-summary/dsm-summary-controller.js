(function() {
    var dsmSummary = sellInNamespace('sellIn.pages.dsmSummary');

    function DsmSummaryController($scope, $routeParams, dsmProfilesResource) {

        var dsm = {dsmId: $routeParams.dsmId};
        dsmProfilesResource.query(dsm).then(function(profiles) {
            $scope.profiles = profiles;
        });
    }

    dsmSummary.DsmSummaryController = DsmSummaryController;
})();