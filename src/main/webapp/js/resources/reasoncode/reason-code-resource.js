(function() {
    var reasonCode = sellInNamespace('sellIn.resources.reasoncode');

    function ReasonCodeResource($resource, reasonCodeUrl) {
        this.resource = $resource(reasonCodeUrl);
    }

    ReasonCodeResource.prototype.query = function(role) {
        return this.resource.query(role).$promise;
    };

    reasonCode.ReasonCodeResource = ReasonCodeResource;
})();