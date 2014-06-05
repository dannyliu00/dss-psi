(function() {
    var defaultPage = sellInNamespace('sellIn.pages.default');

    angular.module('sellIn.pages.default', ['sellIn.resources.role'])
        .controller(defaultPage.DefaultPageController);
})();