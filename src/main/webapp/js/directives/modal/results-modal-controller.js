(function () {
    var resultsModal = sellInNamespace('sellIn.directives.resultsmodal');

    function ResultsModalController($scope, $modalInstance, profile) {
        $scope.profile = profile;

        $scope.okay = function () {
            $modalInstance.close(profile);
        };
    }

    resultsModal.ResultsModalController = ResultsModalController;
})();