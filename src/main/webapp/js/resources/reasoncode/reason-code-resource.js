(function() {
    var reasonCode = sellInNamespace('sellIn.resources.reasoncode');

    function ReasonCodeResource($resource, reasonCodeUrl) {
        this.resource = $resource(reasonCodeUrl);
    }

    ReasonCodeResource.prototype.get = function(role) {
        return this.resource.query(role).$promise;
    };

    reasonCode.ReasonCodeResource = ReasonCodeResource;
})();