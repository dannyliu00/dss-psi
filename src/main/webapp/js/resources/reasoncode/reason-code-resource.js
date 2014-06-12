(function() {
    var reasonCode = sellInNamespace('sellIn.resources.reasoncode');

    function ReasonCodeResource($resource, reasonCodeUrl) {
        this.resource = $resource(reasonCodeUrl);
    }

    ReasonCodeResource.prototype.get = function(roleId) {
        return this.resource.query(roleId).$promise;
    };

    reasonCode.ReasonCodeResource = ReasonCodeResource;
})();