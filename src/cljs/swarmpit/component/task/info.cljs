(ns swarmpit.component.task.info
  (:require [material.component.label :as label]
            [material.component.form :as form]
            [material.component.panel :as panel]
            [material.icon :as icon]
            [rum.core :as rum]))

(enable-console-print!)

(rum/defc form < rum/static [item]
  (let [error (get-in item [:status :error])
        image-digest (get-in item [:repository :imageDigest])]
    [:div
     [:div.form-panel
      [:div.form-panel-left
       (panel/info icon/tasks
                   (:taskName item)
                   (label/info (:state item)))]]
     [:div.form-view
      [:div.form-view-group
       (form/section "General settings")
       (form/item "ID" (:id item))
       (form/item "NAME" (:taskName item))
       (form/item-date "CREATED" (:createdAt item))
       (form/item-date "LAST UPDATE" (:updatedAt item))
       (form/item "IMAGE" (get-in item [:repository :image]))
       (when (some? image-digest)
         (form/item "IMAGE DIGEST" image-digest))]
      [:div.form-view-group
       (form/section "Status")
       (form/item "STATE" (:state item))
       (form/item "DESIRED STATE" (:desiredState item))]
      (when (some? error)
        [:div.form-view-group
         (form/section "Error")
         (form/value error)])]]))