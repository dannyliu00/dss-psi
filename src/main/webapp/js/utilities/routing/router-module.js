(function () {
    var routing = sellInNamespace('sellIn.routing');

    angular.module('sellIn.routing', ['ngRoute', 'sellIn.routing.paths'])
        .config(routing.routerConfig);

}());