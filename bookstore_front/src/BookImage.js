import React, {useEffect, useState} from "react";

export default function BookImage(props) {

    const [img, setImg] = useState();

    const fetchImage = async () => {
        const res = await fetch("http://localhost:8090/api/books/get/photo/" + props.filePath);
        const imageBlob = await res.blob();
        const imageObjectURL = URL.createObjectURL(imageBlob);
        setImg(imageObjectURL);
    };

    useEffect(() => {
        fetchImage();
    }, []);

    return (
        <>
            <img src={img} height={80} alt="bookImage"/>
        </>
    );
}
