(function () {
    var reasonRequired = sellInNamespace('sellIn.directives.reasonrequired');

    function ReasonRequiredController($scope, $modalInstance) {

        $scope.closeModal = function () {
            $modalInstance.close();
        };
    }

    reasonRequired.ReasonRequiredController = ReasonRequiredController;
})();