(function () {
    var dealerInfo = sellInNamespace('sellIn.directives.dealerInfo');

    function DealerInfoController($scope, $routeParams) {

        var caption = '';

        $scope.roleCaption = function () {
            if ($routeParams.type === '5' || $routeParams.type === 'F') {
                caption = 'DRM';
            } else {
                caption = 'DSM';
            }
            return caption;
        };
    }

    dealerInfo.DealerInfoController = DealerInfoController;
}());