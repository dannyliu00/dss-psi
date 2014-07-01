(function() {
    var submitValues = sellInNamespace('sellIn.directives.submitValues');

    function SubmitController($scope, $modalInstance, dealerProfileDetailsResource, data) {

        $scope.submit = function () {
            dealerProfileDetailsResource.submit(data)
                .then(function(returnedData) {
                    if($modalInstance.open()) {
                        $modalInstance.close();
                    }
                });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    submitValues.SubmitController = SubmitController;
})();