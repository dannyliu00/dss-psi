(function() {
    var saveQuantities = sellInNamespace('sellIn.directives.savequantities');

    function SaveQuantitiesController($scope, $modalInstance) {
        $scope.saveChanges = function () {
            $modalInstance.close();
        };
        
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    saveQuantities.SaveQuantitiesController = SaveQuantitiesController;
})();