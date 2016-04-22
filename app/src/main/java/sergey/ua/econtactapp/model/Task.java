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

package sergey.ua.econtactapp.model;

import java.util.Date;


public class Task {

    private String mCategory;
    private String mState;
    private String mTitle;
    private Date mCreatedDate;
    private Date mRegisteredDate;
    private Date mDueDate;
    private String mResponsible;
    private String[] mPictures;
    private String mDescription;
    private Integer mPlusCount;
    private String mAddress;

    public String getAddress() {
        return mAddress;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getCreatedDate() {
        return mCreatedDate;
    }

    public Date getRegisteredDate() {
        return mRegisteredDate;
    }

    public Date getDueDate() {
        return mDueDate;
    }

    public String getResponsible() {
        return mResponsible;
    }

    public String[] getPictures() {
        return mPictures;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getStateName() {
        return StatesTask.getStateName(mState);
    }


    public String getCategory() {
        return mCategory;
    }

    public String getState() {
        return mState;
    }

    public Integer getPlusCount() {
        return mPlusCount;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public void setState(String mState) {
        this.mState = mState;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setCreatedDate(Date mCreatedDate) {
        this.mCreatedDate = mCreatedDate;
    }

    public void setRegisteredDate(Date mRegisteredDate) {
        this.mRegisteredDate = mRegisteredDate;
    }

    public void setDueDate(Date mDueDate) {
        this.mDueDate = mDueDate;
    }

    public void setResponsible(String mResponsible) {
        this.mResponsible = mResponsible;
    }

    public void setPictures(String[] mPictures) {
        this.mPictures = mPictures;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setPlusCount(Integer mPlusCount) {
        this.mPlusCount = mPlusCount;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }
}
