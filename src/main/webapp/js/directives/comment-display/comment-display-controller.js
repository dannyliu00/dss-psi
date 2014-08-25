(function () {
    var commentDisplay = sellInNamespace('sellIn.directives.commentdisplay');

    function CommentDisplayController($scope) {

        var showGuide = { dealer: '',
            dsm: '',
            rsm: ''
        };

        function show() {
            if ($scope.role.rsm === true || $scope.role.dsm === true) {
                showGuide.dealer = true;
                showGuide.dsm = true;
                showGuide.rsm = true;
            } else if ($scope.role.dealer === true && ($scope.profile.status === 'RETURNED TO DEALER' || $scope.profile.status === 'APPROVED')) {
                showGuide.dealer = true;
                showGuide.dsm = true;
                showGuide.rsm = false;
            } else {
                showGuide.dealer = true;
                showGuide.dsm = false;
                showGuide.rsm = false;
            }
            return showGuide;
        }

        $scope.showComments = show();
        $scope.viewDealerComments = $scope.profile.orderSegments[0].dealerComments;
        $scope.viewDsmComments = $scope.profile.orderSegments[0].dsmComments;
        $scope.viewRsmComments = $scope.profile.orderSegments[0].adminComments;
    }

    commentDisplay.CommentDisplayController = CommentDisplayController;
})();