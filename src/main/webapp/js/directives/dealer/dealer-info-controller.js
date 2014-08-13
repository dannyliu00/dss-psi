(function () {
    var dealerInfo = sellInNamespace('sellIn.directives.dealerInfo');

    function DealerInfoController($scope, $routeParams) {

        var caption = '';
        
        var currentType = $routeParams.type;
        
        $scope.roleCaption = function (currentType) {
            if (currentType === '5' || currentType === 'F') {
                caption = 'DRM';
            } else {
                caption = 'DSM';
            }
            return caption;
        };
    }

    dealerInfo.DealerInfoController = DealerInfoController;
})();