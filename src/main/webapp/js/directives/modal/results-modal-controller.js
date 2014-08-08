(function() {
	var resultsModal = sellInNamespace('sellIn.directives.resultsmodal');

    function ResultsModalController($scope, $modalInstance) {
    	
        $scope.okay = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    resultsModal.ResultsModalController = ResultsModalController;
})();