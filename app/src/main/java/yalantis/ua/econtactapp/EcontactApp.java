/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Sergey
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package yalantis.ua.econtactapp;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import yalantis.ua.econtactapp.initilisator.AppInitializer;
import yalantis.ua.econtactapp.initilisator.ContextProvider;
import yalantis.ua.econtactapp.initilisator.FontInitializer;
import yalantis.ua.econtactapp.initilisator.ImageLoaderInitializer;


public class EcontactApp extends Application {
    private List<AppInitializer> mInitializer = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mInitializer.add(new ImageLoaderInitializer());
        mInitializer.add(new FontInitializer());
        mInitializer.add(new ContextProvider());
        for (AppInitializer initializer : mInitializer) {
            initializer.initialize(this);
        }
    }
}
