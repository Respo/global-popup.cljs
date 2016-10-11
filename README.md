
Respo Global Popup
----

Modal and popup component for Respo apps.

Demo http://repo.respo.site/global-popup/

### Usage

[![Clojars Project](https://img.shields.io/clojars/v/respo/global-popup.svg)](https://clojars.org/respo/global-popup)

```clojure
[respo/global-popup "0.1.0"]
```

This popup library contains **modal** and **popover** since these two are coupled from each other. The design of this library is inherited from [routed-modal](https://github.com/origami-ui/routed-modal) which is a React.hs library.

This library is in several parts due to Modal-View-Seperation. It can be tedious to use:

* Model

Before any popup is rendered, there's a `[]` in the `:popups` field in the store:

```clojure
(def store
  {:popups []})
```

A popup looks like this in the Model:

```clojure
(def popup {:name nil, :type :modal, :id nil, :position nil, :data nil})
```

There are 3 actions it will use. The updater functions locates in `'global-popup.updater.popup`:

```clojure
(defn updater [store op op-data op-id]
  (case
    op
    :popup/add
    (popup/add-one store op-data op-id)
    :popup/drop
    (popup/drop-one store op-data op-id)
    :popup/clear-float
    (popup/clear-float store op-data op-id)
    store))
```

* View

There's component called `'global-popup.comp.popup-stack/comp-popup-stack` to render the popups.

```clojure
(comp-popup-stack (:popups store) render-popup)
```

You have to provide `render-popup` function to return virtual DOM piece of the content you want to insert into the model of the popover:

```clojure
(render-popup popup)
; (div {})
```

* Controller

To create a new modal or popover, just dispatch actions like:

```clojure
(defn on-modal-add [e dispatch!]
  (dispatch! :popup/add {:name :demo, :type :modal}))
```

```clojure
(defn on-popover-add [e dispatch!]
  (let [event (:original-event e)]
    (.stopPropagation event)
    (dispatch!
      :popup/add
      {:name :demo,
       :type :popover,
       :position {:y (.-clientY event), :x (.-clientX event)}})))
```

Actions for closing them are dispatched from the given component. However, there's special case, if you want to close popovers by clicking the body of the page, you need to add these lines:

```clojure
(.addEventListener
  js/window
  "click"
  (fn [event]
    (if (not (empty? (:popups @store-ref)))
      (dispatch! :popup/clear-float nil))))
```

Quite some code to add. The benefit is you get the Model and View decoupled, and no private states.

`ESC` shortcut is not supported yet. But you can handle it by adding an event listener and a new updater function.

### Develop

Workflow https://github.com/mvc-works/stack-workflow

### License

MIT
