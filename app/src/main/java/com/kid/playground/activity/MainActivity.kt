package com.kid.playground.activity

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kid.playground.R
import java.io.File
import java.nio.charset.Charset
import java.util.*


class MainActivity : Activity() {

    var imageUrls = """
        https://blupassionsystem.de/data/koblenz/56068_2/element/8219aed1-93b9-4994-ab80-ff41fc328c19.png
        https://blupassionsystem.de/data/koblenz/56068_2/element/e61cee24-bea0-4f4f-921a-fd9cb67f1a07.png
        https://blupassionsystem.de/data/koblenz/56068_2/element/1066d8aa-b06c-427d-a556-715bd1a088c4.png
        https://blupassionsystem.de/data/koblenz/56068_2/element/a0d50bd4-4c16-44d0-b94b-c050440c9650.png
        https://blupassionsystem.de/data/koblenz/56068_2/element/8ce9ded6-24af-49f9-84ce-c4ecc9ffce2d.png
        https://blupassionsystem.de/data/koblenz/56068_2/basepicture/9465452d-f07e-4543-8f65-e1417e640ab8.png
        https://blupassionsystem.de/data/koblenz/56068_2/basepicture/be0f21ed-c06a-4a9d-9d51-7f0b2da276a9.png
        https://blupassionsystem.de/data/koblenz/56068_2/element/f1a8a394-e785-4f28-a4ec-7b6e37525038.png
        https://blupassionsystem.de/data/koblenz/56068_2/element/71d23c02-5a67-4123-a15b-d845d470bc26.png
        https://blupassionsystem.de/data/koblenz/56068_2/element/copy_29991_1677160258612_fdcbcd22-7404-475b-ab59-1d02ba1c7cb3.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/element/copy_29994_1668500827020_f003cc6d-6d7a-4848-81c3-da5e74571e48.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/element/copy_30008_1668500920444_932a11b1-308b-4c49-9a80-e0dade246899.png
        https://blupassionsystem.de/data/koblenz/56068_2/element/copy_29995_1668500827023_80fae872-03aa-4d5c-a3b1-0a461314161e.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/element/copy_29992_1677160258617_91d77ece-6bed-4a9e-84f6-a7d193f6ea10.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/element/copy_34995_1699605236151_bd720a65-6b50-4147-b667-e0fc0518c53e.png
        https://blupassionsystem.de/data/koblenz/56068_2/basepicture/copy_785_1665495136197_db2bf4a9-64fd-4a41-9f35-996740bd7947.png
        https://blupassionsystem.de/data/koblenz/56068_2/element/77070ef8-5eef-47e1-bcca-6b6698d76006.png
        https://blupassionsystem.de/data/koblenz/56068_2/element/copy_30009_1668500920448_c9428bef-f3a2-4183-a064-50be5682089f.png
        https://blupassionsystem.de/data/koblenz/56068_2/element/d69ab396-2f59-4886-bd85-65dcd703d9c8.png
        https://blupassionsystem.de/data/koblenz/56068_2/element/35ab58ca-94f8-42ff-9be4-f2ad00f6fcad.jpeg
        https://blupassionsystem.de/data/koblenz/56068_2/element/2555332e-c542-45cb-bd62-a9851d5acbbd.png
        https://blupassionsystem.de/data/koblenz/56068_2/basepicture/copy_804_1665495136285_3246b3c8-a894-457a-bbe2-487570b5edae.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/basepicture/copy_850_1665495136460_e597cad7-8515-405c-b241-a4a677c81cf0.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/basepicture/copy_850_1665495136460_e597cad7-8515-405c-b241-a4a677c81cf0.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/element/5b2cde34-326d-475a-b54e-63c27ccf3c8b.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/bf502e04-b86c-4434-9d49-76f8faf8c0ad.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/basepicture/copy_863_1665495136515_b3976820-e008-4a96-b43e-66239f2de70c.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/19c5f728-77d2-4bfb-b487-5721a642c297.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/a6a33acd-3add-4018-9084-adb3b53d7e48.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/55097d17-068a-4f69-89a1-5492d840f627.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/4d502e37-fed2-40e1-b9f4-67de42d72404.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7bb1145e-18d6-42e0-a7f3-0d1fbddb6ac9.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/14bfd6bc-f344-48e5-bd2e-4b523e1aacde.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/adbd30b7-aea8-4da5-8f09-00ed7619d0d0.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/b6968f4d-b668-4e88-935f-8460583e1b05.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/0206c42d-c8de-49a8-9cc0-f576633f1799.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d7b7bc80-03db-41a4-b34e-00d8f99a3e59.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/212790b4-2d07-4364-a5de-765e8bbaf7b6.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/812e6b98-f158-4711-89d9-2b0c7c2a0bda.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/54c957eb-495c-43f2-802d-5ee9556bb74a.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/1b034815-2591-4891-b004-999d65a7c020.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/29c60933-9bd3-4199-94f9-56e8a6fcf92a.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/68ed3770-a65b-4b89-ae71-f560fd0935f8.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d3b0200c-8a2f-451a-90d3-091194a51a6b.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/fe2bb0cb-428d-45ad-ade9-2d36d5fa8279.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d78696de-ec9b-429a-963f-740745ebef89.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/de8edc1b-982e-45cd-b7d6-fa5b102163d1.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/22d201f8-a31f-4c77-877e-022e0ecaba23.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/108a28ee-68b5-47e7-8303-1e828d54d639.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/573bc033-067c-4086-95c8-fc296b435e70.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/36231e6d-de5a-4923-a4ce-150f09e37952.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/2b89fa4a-56ef-4e09-a50e-5c104a922796.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/fb7af9b8-da25-4faf-a91c-595e10884a30.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/13d4d7ff-1937-4b77-b3f2-1d5c66f0958b.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/4f703fe6-1611-4fbd-8c79-839f107a14ec.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/96f0ac39-7aa6-4811-9b5f-15bb59e9bfad.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/1be6e293-ed35-4995-8048-1751a4df2013.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d785c3e4-cdcd-4782-8638-9ac5403c2341.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/357e1c4b-2cf7-4fb0-9001-e68288b6917b.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/e7049956-15fc-4af3-8388-7ac5e2c7675d.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/52641213-b491-4c26-a729-e0adc3c6618c.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/6975b514-09b4-4976-ac85-64fbe9ad4d75.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/5a6f6695-a58c-4c1c-9c25-af1c91fbdb82.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/58a5e5b4-ad6d-45b0-8b59-1866cf3b0f29.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/15e8aab0-b9da-45cd-a6e5-507a470087cb.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/09cf64e2-34d0-45f2-819f-6298d139850b.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/261a6d56-84e8-41cf-92d2-2d4b2f05fd89.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/b9bc0220-c4fa-4a33-b7c1-72af74623468.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/a452ebdc-f829-4c1d-9af4-e2a980ec8e6c.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/1ac52e14-4546-479e-acf4-6871acb6d832.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/f36a6524-1564-4bb8-b258-f70c08419191.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/87dc7130-bb58-4d8f-8fc2-eb3e0acf134e.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/600d21e4-31b7-4382-a737-b225449d02bc.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/3432aac0-209d-4d4b-a5ce-4564c557a9a6.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/90e8c2d5-23da-4a3b-a96c-404e64d24919.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/826eec1c-5274-4cb3-9187-8b3427b16336.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/f2295d54-8714-4027-bbe2-07240d3c10b1.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/af1079b3-2730-4ed5-b2b3-eaa84d5a5937.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/df5f7c79-a747-49a4-91d9-63fdfff005a6.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/b3c342ef-c250-4061-90c3-99cbfd3f5685.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/12b44fea-e742-4c24-a2db-af19500ed724.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/715dc775-72f1-45b6-84f1-aeac03e94ef7.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/f87b0c31-fe17-4559-bcc7-639ef84df42e.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/8a205aea-a904-4e4b-aeee-051123873442.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/43d473e3-3b1c-4f5c-8c98-8aef784afea3.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/c6249b8b-f8c7-4346-ad64-3ef5ceaa5b15.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/40327509-b082-41ae-8b83-db36ececc8b1.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/11be67e2-f08e-49d3-88b7-6dcb04c3c24b.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/6865fc50-2bde-46f9-a94f-b0413a0df983.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/5b7ae9c0-ff2f-44d1-9624-dbbd29353eb5.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/9e637414-2e83-4e8a-9d2e-2617dcc2468a.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/ebc4f00a-259b-4418-a290-80078acdbb74.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/4a344e37-713a-4f47-9dc6-572f88502739.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/cefcfa53-5343-4f10-a9b2-6b8748ed9113.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/5937f53f-8095-4f47-9e4b-aead8717184b.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/0f03224c-0bc9-4be8-8169-bba5cd828970.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/b92cbf2f-f1f9-49a7-bbc0-40e0ba590b3b.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/5b4bde99-9f49-48bc-8c82-6e18682b1eee.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/21e2d8a6-2297-46ca-a4bb-69b0c1696509.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/22a7c7d5-010d-4fd9-b6c3-d72faf148c67.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/62eb9567-d717-4875-a582-2af37e5d2684.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/9f7109b2-7b8f-498a-b2aa-76226cca7e62.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/aecaac0d-584e-4982-acb4-82e98c6dcfb1.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/4528aeb8-95aa-41c8-9e6c-64af2e141cdd.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/1d5d40f7-efe9-4d96-bfb8-54bfa9002542.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/2018e694-54ec-4b7b-8823-91bb647a635e.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/e2329d2a-2247-49af-870f-6fc889dda58e.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/725a5425-7f46-4abe-97ec-acbc6aff8f51.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/910221ba-ed67-4b4b-9935-90aa7f65dde2.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/085e7744-82c6-486c-8249-a256591b6fea.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d9335436-47b4-47d6-a929-f766f9ba37c0.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d20687c1-d5d1-4c53-9f13-788f51fd390f.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/e93d59e6-4815-46d9-ac74-33ca48d7c8ff.jpeg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/ba27eea7-3219-43f4-b1eb-36dc577fbc3e.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/be943bf7-fbac-4dee-a156-0a18ede47059.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d6dea636-6a3f-46e6-8536-ca592a0ce331.jpeg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/1bb20ef4-6fa5-4dbb-bd87-707f101a5297.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/edd9b06d-c8bc-48af-981d-a932129754f1.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/b5a75880-02ca-493c-ac59-97506d7058b3.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/604cea8c-7ed1-4e2f-b862-f10292248507.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/ad39a509-5502-4f1f-91f9-068cccb4dcbf.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7fcc4555-bc6b-4ec5-b288-f53003bf1b63.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/159f55d0-bccf-4d20-addc-712db2314716.jpeg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/181a7613-60eb-45db-9818-d2caeebe9788.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/3328a166-ecad-4494-bbc1-1b857d53e276.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7e1c9dcc-3a3d-449c-a8d3-49b9128712ca.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/e68e655f-4c9f-4469-b391-1c814c32ba80.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/56a4ed12-bd00-424c-9854-96f454f4b499.jpeg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/ed39bf02-8ac5-4510-bdf9-be19aae9b994.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/e1355a83-52ad-4698-83ff-3626f61aa34b.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/058fa8dc-347a-4a8e-b176-d528d052c55f.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/b5db705e-78d5-445b-8787-36bef73e9b20.jpeg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/dd1941ac-824b-4e82-bb7c-cd33274160de.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/155d8e5d-c8bd-442c-abac-403f2f4b2a9e.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/08f2d274-5994-4928-a8f9-a696314cf7f3.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/ded7097c-e1b2-4ba8-a033-5171e32a4b80.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d4b9477a-d14c-4027-98b4-5b6d0dbb99ac.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/67f33acc-d80e-41bb-85d7-cb4107f936b8.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/733b3502-45f5-446a-9318-f3a18babe09a.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/76446c4c-fdc0-4c4d-b60e-b7a5194aa9a5.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d7fa4e2e-4313-49f0-89fc-d1fbd37a3f9e.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/c293c4e3-d873-4e61-be6d-33752da66def.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/2993c068-6a8e-4d2b-b301-b2fe06334faf.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/cc31bfbe-a4e3-40c3-b898-87343fc020de.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/22d379b8-467a-4d6f-8394-4c831e0b6c54.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/65e951af-b68d-4701-b56d-fd39eb50f1cf.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/2199eb27-0ba7-4430-8cbf-1328f0551bae.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/1542d128-51e9-43d8-a3ff-c2eddbef6122.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/264b774c-8dcb-406b-9051-d238642b84e6.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/dab48537-d41f-4ac6-9ea1-868315dfad8c.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/1a9a0074-33d5-4705-be67-6af783ffc632.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7dc7e442-de71-49d2-8c3e-5d60c42eaf50.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/f237bb98-ed63-49d1-b625-e89d702d2024.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/eb482cc0-1e8f-4a6e-8cf7-63a7d334e987.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/f26a6535-3873-404b-8e0c-f3e3eeab96ec.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7073f2b5-a02f-479d-9711-3b1bbe3a67db.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/08ab8ff2-7ef0-431a-bd18-51da0e0c4566.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/b0fb2812-4025-4d03-b880-e67ffe23925c.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/0d1d2f1b-6c2a-40d7-9364-6c45ebd7f3d7.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/41762ccd-fdf5-4a14-998f-1b78dfb9fc8f.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7e700224-7358-456d-a0da-309ed005de45.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d5ac0ddc-617e-42ea-aa18-2e05e5f8c36b.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/ea59e36f-8e85-47eb-8924-4d1d40c198cb.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/de7beea2-cc7b-4f00-a073-6b7b57451431.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/4c811e0d-8764-4b2f-b882-be859cfa8331.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/881b702a-6e5e-41d9-9091-bb4731e08888.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d9ee8172-24a5-45e2-a5b8-82e1986988dc.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/c92f16b0-0942-43b9-aa1f-16a65428d18f.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/717d8349-57bd-402a-a53b-c4a0cf96a40f.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/13b24ea1-e01c-452f-8c06-eafc54ff305f.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7d6a18d6-1e02-49cf-8b88-a941abd618ed.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/5e999a4b-bd9a-487e-ac5c-d3c84ce5ab4f.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/c1da1662-8b72-4f59-ad5c-27cd26c75e75.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/1439e60c-8140-4b39-a1a4-e9663d73708e.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/ba1b977e-047c-4a98-b2a9-596dc0f51a84.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/19be641c-18f0-45c2-9294-db285bfc06cd.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/ebe3fd57-1e7e-4246-97ca-f204303c094a.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/0453d402-d58b-4956-80ea-0e9b9cf74e80.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/b9a79d73-7ac3-4759-b551-a8175994e896.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/4ce99c17-4c22-4bbc-a92f-ca2465301508.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/625d0790-6008-421e-9fcd-44b8fa5cd0fd.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/c1698455-c473-4a37-a846-93a1603349e6.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/55090d3e-73e1-4d51-82a6-f2f69a38190a.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/59808e30-e4b9-4390-9eac-38aaa8e75c96.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/15b80c80-948f-4515-badc-dbf1033d3e6f.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/3b3f49f9-c3ff-45f2-b308-9d9c4cf627d6.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/960ac266-129c-467d-8147-be2bace16a93.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/8ae6df6e-9dae-4de1-a5cd-0e24c7205f68.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/35c96c39-cad2-41bd-b257-977f3a3ac27c.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/af1dc31e-f82f-482e-895c-7d8724789e74.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/e4b4f001-3a1c-4c96-b79b-fde69232b080.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/6c3db08f-4e04-4951-b823-f6c34d202e8d.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7503cf81-a022-46bc-bd0b-b10d304e0f35.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/4e39ba80-6633-4345-a7d7-ebca26512aec.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/24055417-272e-4dd6-b34f-3c9e978228bb.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/c5c65fbf-e93c-475c-929a-bd15e45aeeac.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/545dc43d-e7eb-4e41-8584-4eae43c238e6.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/a7bc7d75-b702-4af5-b218-b3d2df737c81.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/5d915fc9-8526-4efa-b481-eaa3e19bece0.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7a702f91-9df4-4672-a4c0-147c1e788d98.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/89aec360-2c32-49c5-bc8b-3ed21432e9b5.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/48ffa979-3fd9-46ff-902c-8fb4406e8f92.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/93b5d866-8992-4ee8-9d3c-21fae8bbcd92.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/db74dab1-78b1-4f8f-885f-42e71a64389e.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/00e95b6d-8e51-46a9-a855-2a0c0837a1f7.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/97873820-790f-4b4d-adc6-328dc2d3250a.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/9a78bd4c-b7c8-46d0-9208-bfc12ec2bf37.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/4cee21dd-8d48-4e19-b16c-d8cd6dd81909.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/81f79eff-b3ad-47e9-8267-96246e14c194.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/0998ebf6-bbd5-41cd-a504-30c60042431e.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/a686f326-2d2d-41d9-82d8-f5124e46bdc8.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/eac44f16-59ba-4e56-81d1-2c901410a489.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d682c6b6-47de-4f2a-a135-901acc9b11cf.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7cb2dccd-f2ff-46fe-817e-329773e38b6d.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/14c09598-8058-48bd-a3e8-ca57d2eb2191.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/a61d42a8-14f9-4970-b32a-eb0945b094dd.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7e8a8833-7469-4bff-a0d7-8d96bc7333c4.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/06511c64-9e0c-4a3b-89e3-5ad661ca1bf3.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/4f3dbb37-13e8-4c7e-b43c-e2d527070123.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/9a6b64db-7468-42ec-995c-49f0c0485cac.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/1597268f-6da0-4d91-8dce-fc245957491e.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/a2978510-112f-4fec-bd03-28c089c1fff0.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/a2f653f2-1b65-4938-84cf-ccddd1aff1b6.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/00dbd078-6214-4eeb-b432-fe49b0c353b4.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7e6ee157-c5d2-429d-b9a6-51547e5ae543.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/f913a42e-b61f-4b97-8858-b261b64e2e75.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/5fcda6bc-b4f9-4bda-af84-94426ba3f895.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/b6bdfe55-e08e-43be-a98d-533c00d1f321.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/be762b1b-aff0-41cb-a039-81cee15be4a1.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/9c0eccbd-58be-4e20-aeb0-10db00597258.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/a86d4d59-658c-46c8-81af-86f96530b6fb.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/647809d7-f68e-4e0c-bec9-0e6fc7bb858c.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/18d7bbfd-0b44-4df5-a368-17709611ac95.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/0146230c-d853-4c92-9137-67971d7f10c8.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/2dc4dd4b-f969-44eb-8ef4-0713e64fc28d.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d9a85087-4703-4c74-9d52-6b46a3ba1684.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/dbf9ee07-8d3b-4aa4-9b8b-00126aa8500e.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/92786034-9aa8-431e-8337-8f5ad3a07016.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/8b109ffb-3f09-4b78-82c3-03782bc3cc02.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/b4230ea2-7260-4e26-a294-1deb96e51c1d.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/0d95f2dc-ac14-463b-ab19-5fdf96125d2c.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/69ccb02e-779f-4959-a9ed-a8f6c9722901.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/31b527d6-f8c1-421b-83d6-4c19acab2566.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/58cb90fa-0f45-46ce-9d98-970400aa8537.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/57bdbbaa-d2b7-4f1e-b293-a155a5ec303e.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/cba55429-47dc-47fe-a04d-e11a36557791.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/01f77ce1-dd7c-4eb2-920f-746e250bbae7.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/2b797e96-9661-4970-a37a-38f0e6ca27e4.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/68208d2a-62b0-4848-9c31-6e3006a5fbb6.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/a41e7c99-93f7-4742-9d40-b7495aac9ae7.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/ddccfa54-3fa9-4afd-8b7c-8a728375fd09.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7f86260e-97df-4d6a-a66a-3fc0c2089a54.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/ac75399d-e9d9-4951-8927-3165fae92819.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/f9399064-36ca-4b00-8d9c-128f0ad832e9.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/3ec03f2a-212d-4b50-943f-d31d44042eef.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/e3783763-7210-4f6d-9dad-24e24c346e25.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/4ca30149-3806-484b-a4ed-fd82f4ac3ec9.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/8f732dd2-7551-4de0-9bda-de69943214fd.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/126d8155-4f51-4612-a860-4b88a1ae5a4c.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/2fa722ba-0b79-4d2c-a4f7-5f74be512ca3.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/55f77e8c-1268-45dc-a4ea-b5f4b7c4a4f1.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/bcae4b14-1d8e-4feb-a6a7-57f90be27cf0.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/784b5796-505b-46ad-9a50-aba552aa7886.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/e5fb0c50-d5e0-42f4-81e9-ce678172a373.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/4f687a20-6288-497f-924d-6569ec69cc29.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/9e2b6c7d-9af1-40f2-bf2c-4173df48890a.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/077f1161-0b59-41ad-8c59-2d943b931e37.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/f628e011-5e7c-4b2e-b336-aaed4e75b7c5.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/8e59a005-3359-415c-9a3b-a95e8eeb13ed.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/306083ab-cdd6-4e11-b43e-9d597a976ef4.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7581466d-50af-40fa-b224-814f065d3c83.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/a422c51e-5a47-482f-b3d8-c45d44b7a238.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7f6657aa-e3e6-401b-afbf-7a2ea99a7b33.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/107e24da-a5a0-4904-8ce5-e171c6af8271.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/cd0d1240-8b3b-43b8-af35-efcdb45cb16d.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/0cede101-1f90-4ca4-a484-7d2b64ca48de.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/50151ef4-c18a-4e97-a5a4-cdb11e11581e.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/520011fd-4da6-4853-83df-7be459cfd6e9.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/fb447b9f-df05-45e6-a864-052b3f82bb22.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/5f25eae5-3743-4b1b-9ebe-8b2d1c1ec4ce.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/8cf0f794-47ca-4691-932c-fdaba79acffa.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/2b1f3ece-9402-4439-b0c3-1144370e294d.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/8873c9c0-a511-4890-a481-ce34460b0798.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/912ab297-7fd9-4bee-9f62-ddefe888c571.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/07a716fa-85b6-42fb-ba87-ac31625b7116.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/8ea4ae15-76b5-40f1-94d7-43a77fe21ccc.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/3f85b942-7744-4b8b-8335-53acffcac928.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/52703b50-fff3-4866-982c-cddac3ab8d33.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/611c1b45-c298-4b98-ac68-3ff4cb6a1942.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/8f3135ef-87b5-46e3-af0b-8a6dcec42533.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/8f882dbc-4cf4-49db-be8d-9df690501bd6.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/649287c2-fbeb-4d2a-8fa8-2750285edad4.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/58e9da7a-6ac7-4aed-96c7-17c6d2e4a816.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/f7c192e9-ca0c-471d-afe7-0361aa37ba81.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/b06f1841-27d1-4eba-afc4-aadc4f7985fc.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/8d4c48e2-af56-438d-9937-d0b8b01057ea.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/9cc57e81-698f-4f23-b8c2-b89bc38454d3.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/f8662a11-da64-41ad-9882-0e60e631c20d.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/fac9cfdd-723b-4342-a0d8-c597a9e59162.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/3cd70e55-9e1a-440f-9c0b-155e68363b4c.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/6e3fe113-9814-4d29-bca6-f75f50547efc.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/45e4eaa7-00d2-418b-9e0e-0399f9bf13ee.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/1ea6c328-22ac-4c00-aaad-a59ec37738c7.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/58d372e0-c5d8-47ad-8884-693ce58ac659.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/45d8b778-c180-4ada-a3b2-5fa8f4edab3d.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/41568463-b213-4036-ba23-37a6be4c5a82.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/fe0d43fe-76f1-4e87-bddc-97e5eb107a21.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d95e97c4-b374-4302-8ea2-8b11fad61c08.jpeg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/9e195c2e-6ca4-4119-bbe2-3def05fa54b8.jpeg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/34af24d2-b9be-498e-b0c6-c2e4faf0ddd6.jpeg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/c25d621f-8e84-49c3-81b0-563066f522ad.jpeg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/ffc50a9c-5b78-4289-b7b6-f6bf516efe6d.jpeg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/6bd3157b-9410-4b89-8015-0a8b6ac6283b.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/b5673145-0a41-4f4c-b160-5f8b37333d31.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/84e440f3-a584-4da8-92d7-3c11a3e6c80a.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/a382e86c-95bb-4c12-beb0-5db3ccc03163.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/82ed072e-19c1-4b85-8866-c69365556034.jpeg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/0e7ae255-c851-4390-b9b7-74fea9c65760.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/a8f9cadd-7ca6-47cd-bd0e-65f0d67dbb40.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/64b37282-c54e-48cc-b5f6-70b7b7a9a309.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/43fb4b8c-183b-4d37-8dc7-f14c22174225.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/ff111bb2-dcfc-4fb4-a049-9661388f853e.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/5c633a0d-cd32-46e9-a4bf-3741e637abc5.jpeg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/9a5acca4-0e2f-4268-8e6a-2d0c8d850b5a.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/194ca8dd-fcf0-427a-b5e5-18f5661f8f48.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7d98573e-ec89-444b-8e26-d296d19622f3.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/5a1b3763-02d6-4443-b61a-70a4912dfbda.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/6c531608-7a49-4a2d-99cf-fd457fcfcc1d.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/688291e6-2638-46aa-b070-54fa0e44042b.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/03802073-c0cc-4eb4-ac05-49ce9c4e2757.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d759f45b-f964-4933-8496-a5473443b192.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/49a9c932-25d4-427a-96dd-32a1ee725312.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/46a87379-48ba-486f-a4bb-6166a0542f61.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/1890b1a7-268c-4ece-9e25-d36e1fb9669a.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/d8fa7349-7892-473b-ba4c-893c02387ed8.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/def4add7-60fb-43ac-a157-cc9fefe8825e.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/6690b1ca-c510-4ad3-97bf-24905cbf1efc.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/31abc787-3ea3-4e13-88f6-05afb3ba3d8f.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/f7437063-895c-446e-b9ce-ca93fc1b0bd4.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/555b25cc-9720-481e-9f73-1fb978ca4f92.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/64822cc0-0218-4f65-be05-69f329132029.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/4500e6b3-c0b9-4e73-a872-73bc73e090c6.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/384eff47-ca77-40e2-9700-2e9e69328374.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/6ae6e919-204e-4dd0-899e-935c1239842d.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/8ebcca17-88a2-4b52-81f4-2bbadbed135c.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/07ee32a2-0343-43d7-8464-0809a63e19f9.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/14b037cb-b3bc-48fc-be4f-6f2305ac7892.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7c11525e-60cd-44e9-825a-499553325e3c.png
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/5fa83c79-6a38-453a-bbdb-ff5b70bc3ed5.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/adc4109a-f6c2-4587-a722-380a38c07fe4.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/6f9f39e3-86c3-4f88-bfbc-2647c141034e.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/7b642282-04ee-4f86-a606-1a4ac4e871aa.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/47aa2dc3-e0c9-4319-8df0-0c789dde1d29.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/b5bacfce-35d6-46a2-baf3-c4a21d9e0c6b.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/ac3f931f-9a64-42d9-a64e-82f6fa93fdf4.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/63fbb077-6f1f-4a22-8bfa-450f00ffa4a4.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/e7adc085-8004-47ff-8253-b8552b00b68b.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/6eedf3ea-2ad4-412e-98db-16656f323db6.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/a11c6167-06da-44cc-b740-b93c8e49cd86.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/cc2345b1-d99a-4aa3-8f6c-d75105fa87a4.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/2f85a53d-d998-457c-b35b-0d1bbab4385c.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/4e889d79-643c-47af-8339-23024d56db25.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/b5e5dd98-b0ef-4188-b0e3-811455593244.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/1ec76b1c-7fe2-4ace-a034-2910dcf377d1.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/a29cf783-9630-43e2-b454-b16de0bb41f8.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/8539f38e-b577-4312-b56d-90bf4922e6da.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/f6bc3c63-2bac-4d71-8f99-672cdde58c60.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/5adcf43b-cd23-4225-b126-7200d556a1e4.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/51cba277-1a8d-4d0e-b5af-798de42dfe52.JPG
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/45c21087-e525-4177-b1ab-ef36fd91fcf2.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/4538a8e5-1ae7-4783-88b1-6c4a85b3a1ec.jpg
        https://blupassionsystem.de/data/koblenz/56068_2/ardetail/8ba64d4e-d223-45bc-8f8c-7b09ce371c09.jpg
    """.trimIndent().split("\n")

    @Volatile
    var loadedCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val file = File("${application.filesDir.absolutePath}/test.txt")
        if (file.exists()) {
            file.delete()
        }

        """
                                                           <BESTELLUNG>
                                                             <VERSION>2.0</VERSION>
                                                             <STATUS>Bestellung</STATUS>
                                                             <PLATINSHOP_INTERNAL_ORDER_ID>blupassion_order_id</PLATINSHOP_INTERNAL_ORDER_ID>
                                                             <AUFTRAGSNUMMER>blupassion_order_id</AUFTRAGSNUMMER>
                                                             <BESTELLZEIT>2024-12-19 14:48:46</BESTELLZEIT>
                                                             <GESCHAEFTSPARTNER>
                                                               <GPNUMMER/>
                                                               <TRAFFICLIGHT_STATE/>
                                                               <TRAFFICLIGHT_VALUE></TRAFFICLIGHT_VALUE>
                                                               <TRAFFICLIGHT_DATE>19.12.2024</TRAFFICLIGHT_DATE>
                                                               <ADRESSE>
                                                                 <GPANREDE></GPANREDE>
                                                                 <TITEL></TITEL>
                                                                 <TELEFONGESCHAEFTLICH/>
                                                                 <ORT></ORT>
                                                                 <FIRMA/>
                                                                 <LAND></LAND>
                                                                 <EMAIL></EMAIL>
                                                                 <FAX/>
                                                                 <VORNAME></VORNAME>
                                                                 <NACHNAME></NACHNAME>
                                                                 <TELEFONPRIVAT></TELEFONPRIVAT>
                                                                 <TELEFONMOBIL/>
                                                                 <STRASSE>Grüner Weg</STRASSE>
                                                                 <PLZ></PLZ>
                                                               </ADRESSE>
                                                               <COOPERATION_PARTNER_NUMBER></COOPERATION_PARTNER_NUMBER>
                                                               <PARTNER>APP CardLink</PARTNER>
                                                               <GPGEBDATUM></GPGEBDATUM>
                                                               <CUSTOMERID></CUSTOMERID>
                                                             </GESCHAEFTSPARTNER>
                                                             <LIEFERADRESSE>
                                                               <GPANREDE></GPANREDE>
                                                               <TITEL></TITEL>
                                                               <TELEFONGESCHAEFTLICH/>
                                                               <ORT></ORT>
                                                               <LAND>Deutschland</LAND>
                                                               <EMAIL></EMAIL>
                                                               <FAX/>
                                                               <VORNAME></VORNAME>
                                                               <NACHNAME></NACHNAME>
                                                               <TELEFONPRIVAT></TELEFONPRIVAT>
                                                               <TELEFONMOBIL/>
                                                               <FIRMA/>
                                                               <STRASSE></STRASSE>
                                                               <PLZ></PLZ>
                                                             </LIEFERADRESSE>
                                                             <BESTELLTEARTIKEL>
                                                               <ARTIKEL>
                                                                 <PZN>17</PZN>
                                                                 <MENGE>0</MENGE>
                                                                 <PREIS>0</PREIS>
                                                                 <NAME>#PfAPP CardLink</NAME>
                                                                 <POSITIONNOTIZ/>
                                                               </ARTIKEL>
                                                           
                                                             </BESTELLTEARTIKEL>
                                                             <VERSANDKOSTEN>0.0</VERSANDKOSTEN>
                                                             <GESAMTPREIS>0.0</GESAMTPREIS>
                                                             <ZAHLUNG>
                                                               <TYP>Rechnung</TYP>
                                                               <EXTERN_BEZAHLT/>
                                                               <TRANSAKTIONSID/>
                                                               <BLZ/>
                                                               <KONTOINHABER/>
                                                               <KONTONUMMER/>
                                                               <IBAN></IBAN>
                                                               <BIC></BIC>
                                                             </ZAHLUNG>
                                                             <REZEPTFOLGT>NEIN</REZEPTFOLGT>
                                                             <VERTRIEBSWEG>Standardversand</VERTRIEBSWEG>
                                                             <VERTRIEBSWEGID/>
                                                             <VORTEILSCODE>Standardversand</VORTEILSCODE>
                                                             <AUTOVORGFELD>
                                                               <TYP>Rechnung</TYP>
                                                               <CUSTOMERID/>
                                                               <FELD_NAME/>
                                                               <FELD_WERT/>
                                                               <FELD_NR>10000</FELD_NR>
                                                             </AUTOVORGFELD>
                                                           </BESTELLUNG>
        """.trimIndent().byteInputStream(Charset.forName("IBM437")).use { inputStream ->
            val copiedBytes = inputStream.copyTo(file.outputStream(), 1024)
            if (file.length() != copiedBytes) {
                Log.d("chi.trinh","error")
            } else {
                Log.d("chi.trinh","ok")
            }
        }
    }

    private fun loadRandomImages() {
        val iv1 = findViewById<ImageView>(R.id.iv1)
        val iv2 = findViewById<ImageView>(R.id.iv2)
        val iv3 = findViewById<ImageView>(R.id.iv3)
        val iv4 = findViewById<ImageView>(R.id.iv4)
        val iv5 = findViewById<ImageView>(R.id.iv5)
        val iv6 = findViewById<ImageView>(R.id.iv6)
        val iv7 = findViewById<ImageView>(R.id.iv7)
        val iv8 = findViewById<ImageView>(R.id.iv8)

        loadOneImage(imageUrls[Random().nextInt(imageUrls.size)],iv1)
        loadOneImage(imageUrls[Random().nextInt(imageUrls.size)],iv2)
        loadOneImage(imageUrls[Random().nextInt(imageUrls.size)],iv3)
        loadOneImage(imageUrls[Random().nextInt(imageUrls.size)],iv4)
        loadOneImage(imageUrls[Random().nextInt(imageUrls.size)],iv5)
        loadOneImage(imageUrls[Random().nextInt(imageUrls.size)],iv6)
        loadOneImage(imageUrls[Random().nextInt(imageUrls.size)],iv7)
        loadOneImage(imageUrls[Random().nextInt(imageUrls.size)],iv8)
    }

    private fun loadOneImage(url: String, iv: ImageView) {
        Log.v("chi.trinh","loadOneImage: $url")
        Glide.with(applicationContext)
            .load(url)
            .onlyRetrieveFromCache(true)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.v(
                        "chi.trinh",
                        "loadOneImage - onLoadFailed: url = $url" + "\n----e.message: " + e?.message
                    )
                    reloadFailedImage(url, iv)

                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.v("chi.trinh", "loadOneImage - onResourceReady: url = $url")
                    return false
                }
            })
            .into(iv)
    }

    private fun reloadFailedImage(url: String, iv: ImageView) {
        Log.v("chi.trinh","loadOneImage: $url")
        Glide.with(applicationContext)
            .load(url)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.v(
                        "chi.trinh",
                        "reloadFailedImage - onLoadFailed: url = $url" + "\n----e.message: " + e?.message
                    )
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.v("chi.trinh", "reloadFailedImage - onResourceReady: url = $url")
                    return false
                }
            })
            .into(iv)
    }

}