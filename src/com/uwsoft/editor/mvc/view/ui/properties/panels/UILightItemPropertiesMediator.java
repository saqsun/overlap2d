/*
 * ******************************************************************************
 *  * Copyright 2015 See AUTHORS file.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *****************************************************************************
 */

package com.uwsoft.editor.mvc.view.ui.properties.panels;

import com.uwsoft.editor.mvc.view.ui.properties.UIItemPropertiesMediator;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.LightActor;
import com.uwsoft.editor.renderer.data.LightVO;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by azakhary on 4/28/2015.
 */
public class UILightItemPropertiesMediator extends UIItemPropertiesMediator<LightActor, UILightItemProperties> {

    private static final String TAG = UILightItemPropertiesMediator.class.getCanonicalName();
    public static final String NAME = TAG;

    public UILightItemPropertiesMediator() {
        super(NAME, new UILightItemProperties());
    }

    @Override
    protected void translateObservableDataToView(LightActor item) {
        LightVO vo = item.getDataVO();

        viewComponent.setType(vo.type);
        viewComponent.setRayCount(vo.rays);
        viewComponent.setStatic(vo.isStatic);
        viewComponent.setXRay(vo.isXRay);
        viewComponent.setRadius(vo.distance + "");
        viewComponent.setAngle(vo.coneDegree + "");
        viewComponent.setDistance(vo.distance + "");
    }

    @Override
    protected void translateViewToItemData() {
        LightVO vo = observableReference.getDataVO();

        vo.type = viewComponent.getType();
        vo.rays = viewComponent.getRayCount();
        vo.isStatic = viewComponent.isStatic();
        vo.isXRay = viewComponent.isXRay();

        vo.coneDegree = NumberUtils.toFloat(viewComponent.getAngle());

        if(viewComponent.getType() == LightVO.LightType.POINT) {
            vo.distance = NumberUtils.toFloat(viewComponent.getRadius());
        } else {
            vo.distance = NumberUtils.toFloat(viewComponent.getDistance());
        }
    }
}